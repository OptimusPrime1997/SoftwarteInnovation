package cn.edu.sjtu.ipads;

/**
 * @author Daniel
 * @since 2019/3/29 9:34
 */
public class Response<T> {
    public static Response SUCCESS = new Response(0, "OK");
    public static Response FAILED = new Response(-1, "Failed");
    public int resCode;
    public String message;
    public T info;

    public Response() {
        this.resCode = -1;
        this.message = "unknown";
    }

    public Response(int resCode, String message, T info) {
        this.resCode = resCode;
        this.message = message;
        this.info = info;
    }

    public Response(int resCode, String message) {
        this.resCode = resCode;
        this.message = message;
    }

    public Response(T info) {
        this.resCode = 0;
        this.message = "ok";
        this.info = info;
    }

    public static Response fail(String msg) {
        return new Response(-1, msg);
    }
}
