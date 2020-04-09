package com.example.springevents;

// Not mandatory to inherit ApplicationEvent class
public class GenericSpringEvent<T> {
    private T what;
    protected boolean success;

    public GenericSpringEvent(T what, boolean success){
        this.what=what;
        this.success=success;
    }

    public T getWhat() {
        return what;
    }

    public void setWhat(T what) {
        this.what = what;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
