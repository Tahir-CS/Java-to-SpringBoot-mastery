public class CollectionsComparison {
    
    // QUICK REFERENCE GUIDE - Collections Comparison
    
    /*
     * ARRAYLIST<T>
     * - Stores: Single values
     * - Order: YES - maintains insertion order
     * - Access: By index get(0), get(1)...
     * - Duplicates: YES - allowed
     * - Best Operation: get by index
     * - Speed: get O(1), search O(n)
     * - Use Case: Ordered lists, returning data
     * 
     * Example:
     *   ArrayList<String> students = new ArrayList<>();
     *   students.add("Ali");
     *   students.get(0);  // Ali
     * 
     */
    
    /*
     * HASHMAP<K, V>
     * - Stores: Key-Value pairs
     * - Order: NO - unpredictable order
     * - Access: By key get("name")
     * - Keys: Must be UNIQUE - duplicates overwrite
     * - Values: Can have duplicates
     * - Best Operation: get by key
     * - Speed: get O(1) - super fast
     * - Use Case: JSON, lookups, configurations
     * 
     * Example:
     *   HashMap<String, Integer> grades = new HashMap<>();
     *   grades.put("Ali", 95);
     *   grades.get("Ali");  // 95 (instant!)
     * 
     */
    
    /*
     * HASHSET<T>
     * - Stores: Single values (unique only)
     * - Order: NO - unpredictable order
     * - Access: NO index access
     * - Duplicates: NO - automatically prevented
     * - Best Operation: contains() check
     * - Speed: contains O(1) - fastest for membership
     * - Use Case: Tags, roles, finding duplicates
     * 
     * Example:
     *   HashSet<String> tags = new HashSet<>();
     *   tags.add("java");
     *   tags.add("java");  // Silently ignored
     *   tags.contains("java");  // true, O(1)
     * 
     */
    
    public static void main(String[] args) {
        System.out.println("Collections Framework Comparison - See comments above for details");
    }
}
