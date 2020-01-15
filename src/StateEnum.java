public enum StateEnum {
    RISE("rise", "电梯上升"),

    DROP("drop", "电梯下降"),

    FAULT("fault", "电梯故障"),

    FREE("free", "电梯空闲"),

    PAUSE("pause", "电梯暂停"),

    OVERWEIGHT("weight", "超重"),


    CHECKED("checked", "电梯被选中");


    private String type;
    private String description;

    StateEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }


    @SuppressWarnings("unused")
    public String getDescription() {
        return description;
    }

}
