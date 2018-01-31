package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;


@XmlType(propOrder = {"name", "searchLabel","fields", "align", "width", "height"})
@XmlRootElement(name="result-param")
public class ResultParam {
    private String name;
    private String searchLabel;
    private List<Field> fields;
    private String align;
    private String width;
    private String height;

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getSearchLabel() {
        return searchLabel;
    }

    @XmlElement(name = "search-label")
    public void setSearchLabel(String searchLabel) {
        this.searchLabel = searchLabel;
    }

    public List<Field> getFields() {
        return fields;
    }

    @XmlElement(name = "fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getAlign() {
        return align;
    }

    @XmlElement(name = "align")
    public void setAlign(String align) {
        this.align = align;
    }

    public String getWidth() {
        return width;
    }

    @XmlElement(name = "width")
    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    @XmlElement(name = "height")
    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ResultParam{" +
                "name='" + name + '\'' +
                ", searchLabel='" + searchLabel + '\'' +
                ", align='" + align + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
