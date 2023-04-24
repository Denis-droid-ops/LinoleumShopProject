package com.kuznetsov.linoleum.entity;

public class Fragment {
    private Integer id;
    private Float width;
    private Float length;
    private Layout layout;

    public Fragment() {
    }

    public Fragment(Integer id, Float width, Float length, Layout layout) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.layout = layout;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "Fragment{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", layout=" + layout +
                '}';
    }
}
