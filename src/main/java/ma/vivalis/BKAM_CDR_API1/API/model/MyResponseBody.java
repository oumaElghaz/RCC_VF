package ma.vivalis.BKAM_CDR_API1.API.model;

public class MyResponseBody {
    private int id_Lot;
    private String codMsg;
    private String msg;

    // Constructeurs
    public MyResponseBody() {}
    public MyResponseBody(int id_Lot,String codMsg,String msg) {
        this.id_Lot = id_Lot;
        this.codMsg = codMsg;
        this.msg=msg;
    }
    //getters
    public int getId_Lot() {
        return id_Lot;
    }

    public String getCodMsg() {
        return codMsg;
    }

    public String getMsg() {
        return msg;
    }
    //setters
    public void setId_Lot(int id_Lot) {
        this.id_Lot = id_Lot;
    }

    public void setCodMsg(String codMsg) {
        this.codMsg = codMsg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
