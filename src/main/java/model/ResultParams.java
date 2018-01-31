package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="result-params")
public class ResultParams {
    private List<ResultParam> resultParams;

    public List<ResultParam> getResultParams() {
        return resultParams;
    }

    @XmlElement( name = "result-param" )
    public void setResultParams(List<ResultParam> resultParams) {
        this.resultParams = resultParams;
    }
}
