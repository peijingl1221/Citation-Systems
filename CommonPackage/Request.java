// Used by the client to send requests for data  to the server

package CommonPackage;

public class Request {
    RequestType request;
    FieldType field;
    Citation citation;

    public Request(){
        request = null;
        field = null;
        citation = null;
    }

    public Request(RequestType request, FieldType field, Citation citation) {
        this.request = request;
        this.field = field;
        this.citation = citation;
    }

    public void setRequestType(RequestType request) {
        this.request = request;
    }
    public void setFieldType(FieldType field) {this.field = field;}
    public void setCitation(Citation citation) {this.citation = citation;}
    public RequestType getRequestType() {
        return request;
    }
    public FieldType getFieldType() {
        return field;
    }
    public Citation getCitation() {
        return citation;
    }
}
