package co.spribe.pc.api.request;

import co.spribe.pc.api.Request;
import co.spribe.pc.api.constants.ConstantsURL;

import java.util.Map;

public class AbstractRequest implements Request {

    protected static Map<String, Object> getPathParams(String editor) {
        return Map.of(ConstantsURL.EDITOR_QUERY_PARAM, editor);
    }

    protected static Map<String, Object> getPathParams(String editor, Integer id) {
        return Map.of(
                ConstantsURL.EDITOR_QUERY_PARAM, editor,
                ConstantsURL.ID_QUERY_PARAM, id);
    }
}
