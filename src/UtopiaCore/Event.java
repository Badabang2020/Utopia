public interface Event {
    public void happens(Citizen citizen);
    public void tick();
    public Category[] getCategory();
    //     public Category[] getCategory() {
    //        Category[] category = new Category[] {Category.1, Category.2, ...};
    //        return category;
    //     }
}
