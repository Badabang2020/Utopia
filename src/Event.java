public interface Event {
    public void happens(Citizen citizen);
    public void tick();
    public Category getCategory();
}
