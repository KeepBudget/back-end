package kb.KeepBudget.property.type;

import lombok.Getter;

@Getter
public enum TradeType {
    DEAL("매매"),
    LEASE("전세");

    private final String name;

    TradeType(String name){
        this.name = name;
    }


}
