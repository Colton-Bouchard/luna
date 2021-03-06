package io.luna.game.model.item.shop;

/**
 * The enumerated type whose elements represent different ways that items can be sold to shops.
 *
 * @author lare96 <http://github.com/lare96>
 */
public enum SellPolicy {

    /**
     * Shop will buy all items.
     */
    ALL,

    /**
     * Shop will only buy items it has in stock.
     */
    EXISTING,

    /**
     * Shop will not buy any items.
     */
    NONE
}