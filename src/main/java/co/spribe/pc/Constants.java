package co.spribe.pc;

public class Constants {
    public static final String BASE_URL = "http://3.68.165.45/";
    public static final String EDITOR_QUERY_PARAM = "editor";
    public static final String ID_QUERY_PARAM = "id";

    public static final String CREATE_URI = "/player/create/{"+EDITOR_QUERY_PARAM+"}";
    public static final String GET_URI = "/player/get/";
    public static final String GET_ALL_URI = "/player/get/all";
    public static final String UPDATE_URI = "/player/update/{"+EDITOR_QUERY_PARAM+"}/{id}";
    public static final String DELETE_URI = "/player/delete/{"+EDITOR_QUERY_PARAM+"}";


    public static final String OG_SUPERVISOR = "supervisor";
    public static final String SUPERVISOR = "dn_super";
    public static final String ADMIN = "dn_admin";
    public static final String PLAYER = "dn_player";

    public static final String ROLE_SUPERVISOR = "supervisor";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_PLAYER = "user";

    public static final Integer OG_SUPER_ID = 1;
}
