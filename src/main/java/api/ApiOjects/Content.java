package api.ApiOjects;

import lombok.Getter;

import java.util.List;

@Getter
public class Content {

    List<Items> items;

    @Getter
    class Items {
        String id;
        Item item;
    }

    @Getter
    class Item {
        String name;
    }
}
