package com.ser322deliverable4.dto;


public class AddModelDto {

    private String name;

    private String year;

    private String style;

    private String trimLevelName;

    public AddModelDto() {
    }

    public AddModelDto(String name, String year, String style, String trimLevelName) {
        this.name = name;
        this.year = year;
        this.style = style;
        this.trimLevelName = trimLevelName;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getStyle() {
        return style;
    }

    public String getTrimLevelName() {
        return trimLevelName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setTrimLevelName(String trimLevelName) {
        this.trimLevelName = trimLevelName;
    }
}
