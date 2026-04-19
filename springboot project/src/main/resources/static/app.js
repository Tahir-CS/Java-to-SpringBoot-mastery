// Central state object for students loaded from backend.
const state = {
    students: []
};

// Cache DOM references once to keep code clean and fast.
const elements = {
    healthStatus: document.getElementById("healthStatus"),
    studentCount: document.getElementById("studentCount"),
    lastUpdated: document.getElementById("lastUpdated"),
    studentForm: document.getElementById("studentForm"),
    nameInput: document.getElementById("nameInput"),
    emailInput: document.getElementById("emailInput"),
    searchInput: document.getElementById("searchInput"),
    studentList: document.getElementById("studentList"),
    submitBtn: document.getElementById("submitBtn"),
    refreshBtn: document.getElementById("refreshBtn"),
    toast: document.getElementById("toast")
};

function showToast(message, type = "ok") {
    elements.toast.textContent = message;
    elements.toast.className = `toast show ${type}`;

    window.clearTimeout(showToast.timerId);
    showToast.timerId = window.setTimeout(() => {
        elements.toast.className = "toast";
    }, 2200);
}

function updateLastUpdatedTime() {
    const now = new Date();
    elements.lastUpdated.textContent = now.toLocaleTimeString();
}

function escapeHtml(value) {
    // Basic escaping so user input never renders as HTML.
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

function updateHealthUI(isUp, message) {
    elements.healthStatus.classList.remove("pending", "up", "down");
    elements.healthStatus.classList.add(isUp ? "up" : "down");
    elements.healthStatus.textContent = message;
}

function renderStudents(studentsToRender) {
    if (!studentsToRender.length) {
        elements.studentList.innerHTML = "<p class=\"empty-state\">No students found.</p>";
        return;
    }

    elements.studentList.innerHTML = studentsToRender
        .map(student => {
            const safeName = escapeHtml(student.name);
            const safeEmail = escapeHtml(student.email);
            return `
                <article class="student-card">
                    <div>
                        <h3>${safeName}</h3>
                        <p class="student-meta">ID: ${student.id} | ${safeEmail}</p>
                    </div>
                    <button class="btn btn-delete" data-id="${student.id}" type="button">Delete</button>
                </article>
            `;
        })
        .join("");
}

function applySearchFilter() {
    const query = elements.searchInput.value.trim().toLowerCase();

    const filtered = state.students.filter(student => {
        const nameMatch = student.name.toLowerCase().includes(query);
        const emailMatch = student.email.toLowerCase().includes(query);
        return nameMatch || emailMatch;
    });

    renderStudents(filtered);
}

async function fetchJson(url, options = {}) {
    const response = await fetch(url, options);

    let payload = null;
    try {
        payload = await response.json();
    } catch (error) {
        // Some responses may not include JSON; ignore parse errors here.
    }

    if (!response.ok) {
        const message = payload?.error || payload?.message || `Request failed with status ${response.status}`;
        throw new Error(message);
    }

    return payload;
}

async function loadHealth() {
    try {
        const data = await fetchJson("/api/health");
        const message = data.status === "UP" ? "Healthy" : "Unhealthy";
        updateHealthUI(data.status === "UP", message);
    } catch (error) {
        updateHealthUI(false, "Down");
        showToast(error.message, "error");
    }
}

async function loadStudents() {
    elements.studentList.innerHTML = "<p class=\"empty-state\">Loading students...</p>";

    try {
        const students = await fetchJson("/api/students");
        state.students = Array.isArray(students) ? students : [];
        elements.studentCount.textContent = String(state.students.length);
        applySearchFilter();
        updateLastUpdatedTime();
    } catch (error) {
        elements.studentList.innerHTML = `<p class="empty-state">${escapeHtml(error.message)}</p>`;
        showToast(error.message, "error");
    }
}

async function handleCreateStudent(event) {
    event.preventDefault();

    const name = elements.nameInput.value.trim();
    const email = elements.emailInput.value.trim();

    if (!name || !email) {
        showToast("Name and email are required.", "error");
        return;
    }

    elements.submitBtn.disabled = true;
    elements.submitBtn.textContent = "Saving...";

    try {
        await fetchJson("/api/students", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name, email })
        });

        elements.studentForm.reset();
        await loadStudents();
        showToast("Student created successfully.", "ok");
    } catch (error) {
        showToast(error.message, "error");
    } finally {
        elements.submitBtn.disabled = false;
        elements.submitBtn.textContent = "Save Student";
    }
}

async function handleDeleteStudent(studentId) {
    const confirmDelete = window.confirm("Delete this student?");
    if (!confirmDelete) return;

    try {
        await fetchJson(`/api/students/${studentId}`, {
            method: "DELETE"
        });
        await loadStudents();
        showToast("Student deleted successfully.", "ok");
    } catch (error) {
        showToast(error.message, "error");
    }
}

function attachEvents() {
    // Create student form submit.
    elements.studentForm.addEventListener("submit", handleCreateStudent);

    // Live search inside already-fetched list.
    elements.searchInput.addEventListener("input", applySearchFilter);

    // Refresh button pulls fresh health + students.
    elements.refreshBtn.addEventListener("click", async () => {
        elements.refreshBtn.disabled = true;
        elements.refreshBtn.textContent = "Refreshing...";
        await Promise.all([loadHealth(), loadStudents()]);
        elements.refreshBtn.disabled = false;
        elements.refreshBtn.textContent = "Refresh Data";
    });

    // Event delegation for dynamic delete buttons.
    elements.studentList.addEventListener("click", event => {
        const deleteButton = event.target.closest("button[data-id]");
        if (!deleteButton) return;

        const studentId = Number(deleteButton.dataset.id);
        if (!Number.isFinite(studentId)) return;

        handleDeleteStudent(studentId);
    });
}

async function init() {
    attachEvents();
    await Promise.all([loadHealth(), loadStudents()]);
}

document.addEventListener("DOMContentLoaded", init);
