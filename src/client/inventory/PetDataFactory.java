/*
 This file is part of the OdinMS Maple Story Server
 Copyright (C) 2008 ~ 2010 Patrick Huy <patrick.huy@frz.cc> 
 Matthias Butz <matze@odinms.de>
 Jan Christian Meyer <vimes@odinms.de>

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License version 3
 as published by the Free Software Foundation. You may not use, modify
 or distribute this program under any other version of the
 GNU Affero General Public License.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package client.inventory;

import java.util.HashMap;
import java.util.Map;
import provider.MapleData;
import provider.MapleDataProvider;
import provider.MapleDataProviderFactory;
import provider.MapleDataTool;
import tools.Pair;

public class PetDataFactory {

    private static final MapleDataProvider dataRoot = MapleDataProviderFactory.getDataProvider("Item.wz");
    private static final Map<Pair<Integer, Integer>, PetCommand> petCommands = new HashMap<>();
    private static final Map<Integer, Integer> petHunger = new HashMap<>();

    public static final PetCommand getPetCommand(final int petId, final int skillId) {
        PetCommand ret = petCommands.get(new Pair<>(Integer.valueOf(petId), Integer.valueOf(skillId)));
        if (ret != null) {
            return ret;
        }
        final MapleData skillData = dataRoot.getData("Pet/" + petId + ".img");
        int prob = 0;
        int inc = 0;
        if (skillData != null) {
            prob = MapleDataTool.getInt("interact/" + skillId + "/prob", skillData, 0);
            inc = MapleDataTool.getInt("interact/" + skillId + "/inc", skillData, 0);
        }
        ret = new PetCommand(petId, skillId, prob, inc);
        petCommands.put(new Pair<>(petId, skillId), ret);

        return ret;
    }

    public static final int getHunger(final int petId) {
        Integer ret = petHunger.get(Integer.valueOf(petId));
        if (ret != null) {
            return ret;
        }
        final MapleData hungerData = dataRoot.getData("Pet/" + petId + ".img").getChildByPath("info/hungry");
        ret = MapleDataTool.getInt(hungerData, 1);
        petHunger.put(petId, ret);

        return ret;
    }
}
