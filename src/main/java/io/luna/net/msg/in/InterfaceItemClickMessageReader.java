package io.luna.net.msg.in;

import io.luna.game.event.Event;
import io.luna.game.model.item.Item;
import io.luna.game.model.item.shop.ShopInterface;
import io.luna.game.model.mob.Player;
import io.luna.game.model.mob.PlayerRights;
import io.luna.game.model.mob.inter.AmountInputInterface;
import io.luna.net.codec.ByteMessage;
import io.luna.net.codec.ByteOrder;
import io.luna.net.codec.ByteTransform;
import io.luna.net.msg.GameMessage;
import io.luna.net.msg.GameMessageReader;

import static com.google.common.base.Preconditions.checkState;

/**
 * A {@link GameMessageReader} implementation that intercepts data sent on interface item clicks.
 *
 * @author lare96 <http://github.org/lare96>
 */
public final class InterfaceItemClickMessageReader extends GameMessageReader {

    @Override
    public Event read(Player player, GameMessage msg) throws Exception {
        int opcode = msg.getOpcode();
        switch (opcode) {
            case 145:
                firstIndex(player, msg.getPayload());
                break;
            case 117:
                secondIndex(player, msg.getPayload());
                break;
            case 43:
                thirdIndex(player, msg.getPayload());
                break;
            case 129:
                fourthIndex(player, msg.getPayload());
                break;
            case 135:
                fifthIndex(player, msg.getPayload());
                break;
        }
        return null;
    }

    /**
     * The first index click.
     *
     * @param player The player.
     * @param msg The buffer to read from.
     */
    private void firstIndex(Player player, ByteMessage msg) {
        int interfaceId = msg.getShort(ByteTransform.A);
        int index = msg.getShort(ByteTransform.A);
        int itemId = msg.getShort(ByteTransform.A);

        checkState(interfaceId > 0, "interfaceId <= 0");
        checkState(index >= 0, "index < 0");
        checkState(itemId > 0, "itemId <= 0");

        switch (interfaceId) {
            case 1688:
                player.getEquipment().unequip(index);
                break;
            case 5064:
                if (player.getBank().isOpen()) {
                    player.getBank().deposit(index, 1);
                }
                break;
            case 5382:
                if (player.getBank().isOpen()) {
                    player.getBank().withdraw(index, 1);
                } else if (player.getRights().equalOrGreater(PlayerRights.DEVELOPER)) {
                    player.getInventory().add(new Item(itemId, 1));
                }
                break;
            case 3900:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().sendBuyValue(player, index));
                break;
            case 3823:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().sendSellValue(player, index));
                break;
            case 3322:
                // Offer 1 item on trade screen
                break;
            case 3415:
                // Remove 1 item from trade screen
                break;
        }
    }

    /**
     * The second index click.
     *
     * @param player The player.
     * @param msg The buffer to read from.
     */
    private void secondIndex(Player player, ByteMessage msg) {
        int interfaceId = msg.getShort(true, ByteTransform.A, ByteOrder.LITTLE);
        int itemId = msg.getShort(true, ByteTransform.A, ByteOrder.LITTLE);
        int index = msg.getShort(true, ByteOrder.LITTLE);

        checkState(interfaceId > 0, "interfaceId <= 0");
        checkState(index >= 0, "index < 0");
        checkState(itemId > 0, "itemId <= 0");

        switch (interfaceId) {
            case 5064:
                if (player.getBank().isOpen()) {
                    player.getBank().deposit(index, 5);
                }
                break;
            case 5382:
                if (player.getBank().isOpen()) {
                    player.getBank().withdraw(index, 5);
                } else if (player.getRights().equalOrGreater(PlayerRights.DEVELOPER)) {
                    player.getInventory().add(new Item(itemId, 5));
                }
                break;
            case 3900:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().buy(player, index, 1));
                break;
            case 3823:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().sell(player, index, 1));
                break;
            case 3322:
                // Add 5 of <item> on trade screen
                break;
            case 3415:
                // Remove 5 of <item> from trade screen
                break;
        }
    }

    /**
     * The third index click.
     *
     * @param player The player.
     * @param msg The buffer to read from.
     */
    private void thirdIndex(Player player, ByteMessage msg) {
        int interfaceId = msg.getShort(ByteOrder.LITTLE);
        int itemId = msg.getShort(ByteTransform.A);
        int index = msg.getShort(ByteTransform.A);

        checkState(interfaceId > 0, "interfaceId <= 0");
        checkState(index >= 0, "index < 0");
        checkState(itemId > 0, "itemId <= 0");

        switch (interfaceId) {
            case 5064:
                if (player.getBank().isOpen()) {
                    player.getBank().deposit(index, 10);
                }
                break;
            case 5382:
                if (player.getBank().isOpen()) {
                    player.getBank().withdraw(index, 10);
                } else if (player.getRights().equalOrGreater(PlayerRights.DEVELOPER)) {
                    player.getInventory().add(new Item(itemId, 10));
                }
                break;
            case 3900:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().buy(player, index, 5));
                break;
            case 3823:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().sell(player, index, 5));
                break;
            case 3322:
                // Add 10 of <item> on trade screen
                break;
            case 3415:
                // Remove 10 of <item> from trade screen
                break;
        }
    }

    /**
     * The fourth index click.
     *
     * @param player The player.
     * @param msg The buffer to read from.
     */
    private void fourthIndex(Player player, ByteMessage msg) {
        int index = msg.getShort(ByteTransform.A);
        int interfaceId = msg.getShort();
        int itemId = msg.getShort(ByteTransform.A);

        checkState(interfaceId > 0, "interfaceId <= 0");
        checkState(index >= 0, "index < 0");
        checkState(itemId > 0, "itemId <= 0");

        switch (interfaceId) {
            case 5064:
                if (player.getBank().isOpen()) {
                    player.getBank().deposit(index, player.getInventory().computeAmountForId(itemId));
                }
                break;
            case 5382:
                if (player.getBank().isOpen()) {
                    player.getBank().withdraw(index, player.getBank().computeAmountForId(itemId));
                } else if (player.getRights().equalOrGreater(PlayerRights.DEVELOPER)) {
                    player.getInventory().add(new Item(itemId, 1_000_000));
                }
                break;
            case 3900:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().buy(player, index, 10));
                break;
            case 3823:
                player.getInterfaces().standardTo(ShopInterface.class).
                        ifPresent(inter -> inter.getShop().sell(player, index, 10));
                break;
            case 3322:
                // Add all of <item> on trade screen
                break;
            case 3415:
                // Remove all of <item> from trade screen
                break;
        }
    }

    /**
     * The fifth index click.
     *
     * @param player The player.
     * @param msg The buffer to read from.
     */
    private void fifthIndex(Player player, ByteMessage msg) {
        int index = msg.getShort(ByteOrder.LITTLE);
        int interfaceId = msg.getShort(false, ByteTransform.A);
        int itemId = msg.getShort(ByteOrder.LITTLE);

        checkState(interfaceId > 0, "interfaceId <= 0");
        checkState(index >= 0, "index < 0");
        checkState(itemId > 0, "itemId <= 0");

        switch (interfaceId) {
            case 5064:
                if (player.getBank().isOpen()) {
                    player.getInterfaces().open(new AmountInputInterface() {
                        @Override
                        public void onAmountInput(Player player, int value) {
                            player.getBank().deposit(index, value);
                        }
                    });
                }
                break;
            case 5382:
                player.getInterfaces().open(new AmountInputInterface() {
                    @Override
                    public void onAmountInput(Player player, int value) {
                        if (player.getBank().isOpen()) {
                            player.getBank().withdraw(index, value);
                        } else if (player.getRights().equalOrGreater(PlayerRights.DEVELOPER)) {
                            player.getInventory().add(new Item(itemId, value));
                        }
                    }
                });
                break;
        }
    }
}
