package com.pProject.ganada;

import com.google.gson.annotations.SerializedName;

public class Caption {
    @SerializedName("kind") // 서버에서 "kind"로 전달되는 데이터를 매핑
    private String kind;

    @SerializedName("message") // 서버에서 "message"로 전달되는 데이터를 매핑
    private String message;

    // Getter와 Setter
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "kind='" + kind + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
