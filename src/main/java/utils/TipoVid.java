package utils;

public enum TipoVid {
    BLANCA("0"), NEGRA("1");

    private String tipo;

    TipoVid(String tipo){
        this.tipo = tipo;
    }

    //Recibe por parametro que tipo de vid es y lo envia a la recoleccion de vid
    public static TipoVid typeVid(String tipo){
        if(tipo.equalsIgnoreCase("negra")){
            return TipoVid.NEGRA;
        }else{
            return TipoVid.BLANCA;
        }
    }

}
