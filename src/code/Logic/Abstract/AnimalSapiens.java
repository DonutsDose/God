package code.Logic.Abstract;

import code.GUI.Main.MainPanel;
import code.GUI.Map.Map;
import code.Logic.Engine.Engine;
import code.Logic.Objects.NewAnimal;
import code.Logic.Objects.Plant;
import code.MyMath.Point;
import code.MyMath.xMath;
import code.MyMath.xRandom;

/**
 * Created by DonutsDose-PC on 26.06.2017.
 */
abstract public class AnimalSapiens extends AnimalPrimitive {

    private static final int TIME_BETWEEN_REPRODUCTION = 30;
    public static final int CREATURE_ANIMAL_BEAR = 13;
    public static final int CREATURE_ANIMAL_WOLF = 14;
    public static final int FIRST_ATTACK_BONUS = 856;

    protected int MAX_SATIETY, AREA_OF_VISIBLE, DELTA_SATIETY;
    protected boolean[] ration = new boolean[Map.MAX_CREATURE_TYPE];

    public AnimalSapiens(Point pos, char face, int color, int type, int PERIOD_OF_PREGNANT, int MAX_ENERGY, int MAX_SATIETY, int PROBABLY_DIE, boolean sex, int AREA_OF_VISIBLE, int PRODUCT_AGE, int calories, int DELTA_SATIETY) {
        super(pos, face, color, type, PERIOD_OF_PREGNANT, MAX_ENERGY, PROBABLY_DIE, sex, calories);
        this.MAX_SATIETY = MAX_SATIETY;
        this.AREA_OF_VISIBLE = AREA_OF_VISIBLE;
        satiety = MAX_SATIETY;
        readyToReproduct = PRODUCT_AGE;
        this.DELTA_SATIETY = DELTA_SATIETY;
        initRation();
    }

    @Override
    public boolean act() {
        if (!super.act()) {
            event("Died of old age.");
            return false;
        }
        satiety -= DELTA_SATIETY;
        if (satiety < 0) {
            event("Died of hunger.");
            return false;
        }
        if (readyToReproduct > 0) readyToReproduct--;
        if (pregnant && readyToReproduct == 0) {
            pregnant = false;
            readyToReproduct = TIME_BETWEEN_REPRODUCTION;
            reproductFunction();
        }
        if (energy < MAX_ENERGY / 4) {
            sleep();
            return true;
        }
        //SCANNING-------
        int eatIndex = -1, partnerIndex = -1;
        Point partner = new Point(0, 0), eat = new Point(0, 0);
        for (int i=0; i<Map.world.creatures.size(); i++)
            if (Map.world.creatures.get(i).exist) {
                //FIND_EAT
                if (canEat(Map.world.creatures.get(i)) && xMath.maxDist(pos, Map.world.creatures.get(i).pos) <= AREA_OF_VISIBLE && canPass(Map.world.creatures.get(i).pos)) {
                    if (eatIndex == -1) {
                        eatIndex = i;
                        eat = new Point(Map.world.creatures.get(i).pos.getX(), Map.world.creatures.get(i).pos.getY());
                    } else {
                        if (xMath.maxDist(pos, Map.world.creatures.get(i).pos) < xMath.maxDist(pos, eat)) {
                            eatIndex = i;
                            eat = new Point(Map.world.creatures.get(i).pos.getX(), Map.world.creatures.get(i).pos.getY());
                        }
                    }
                }
                //FIND_PARTNER
                if (canReproductWith(Map.world.creatures.get(i))) {
                    if (partnerIndex == -1) {
                        partnerIndex = i;
                        partner = new Point(Map.world.creatures.get(i).pos.getX(), Map.world.creatures.get(i).pos.getY());
                    } else {
                        if (xMath.maxDist(pos, Map.world.creatures.get(i).pos) < xMath.maxDist(pos, partner)) {
                            partnerIndex = i;
                            partner = new Point(Map.world.creatures.get(i).pos.getX(), Map.world.creatures.get(i).pos.getY());
                        }
                    }
                }
            }
        //-----------------
        if (((satiety * 3) >> 1) < MAX_SATIETY) {
            if (eatIndex != -1) {
                if (!tryToEat(eat, eatIndex)) {
                    event("Deid by poisoning plants");
                    return false;
                }
            } else moveQuietly();
            return true;
        }
        if (partnerIndex != -1 && readyToReproduct == 0) {
            goToReproduct(partner, partnerIndex);
            return true;
        }
        moveQuietly();
        return true;
    }

    protected void reproductFunction() {
        int cnt = xRandom.getIntInRange(0, 4), res = 0;
        for (int i=pos.getX() - 1; i<=pos.getX() + 1; i++)
            for (int j=pos.getY() - 1; j<=pos.getY() + 1; j++) {
                if (cnt > 0 && xMath.inMap(i, j) && Map.world.checkEmptyPosition(i, j) && canPass(i, j)) {
                    Engine.borned.add(new NewAnimal(new Point(i, j), type));
                    cnt--;
                    res++;
                }
            }
        event("Born " + res + " creatures");
    }

    protected boolean canEat(Creature obj) {
        return (ration[obj.type] && obj.canBeEaten());
    }

    protected boolean canReproductWith(Creature obj) {
        return (obj.type == type && obj.sex != sex && obj.readyToReproduct == 0 && readyToReproduct == 0 && !obj.pregnant && !pregnant);
    }

    protected boolean tryToEat(Point to, int index) {
        if (xMath.maxDist(pos, to) == 1) {
            if (Map.world.creatures.get(index).type == Plant.CREATURE_PLANT_BELLADONNA) return false;
            if (Map.getType(Map.world.creatures.get(index)) == Map.ANIMAL_SAPIENSE) {
                int firstPoints = getBattlePoints() + FIRST_ATTACK_BONUS;
                int secondPoints = Map.world.creatures.get(index).getBattlePoints();
                if (firstPoints > secondPoints) {
                    eatHim(this, Map.world.creatures.get(index));
                } else if (firstPoints < secondPoints) {
                    eatHim(Map.world.creatures.get(index), this);
                }
            } else eatHim(this, Map.world.creatures.get(index));
        } else goTo(to);
        return true;
    }

    protected void eatHim(Creature hunter, Creature victim) {
        hunter.satiety = Math.min(MAX_SATIETY, victim.calories + satiety);
        if (Map.getType(victim) == Map.TREE) {
            victim.eaten();
        } else {
            victim.exist = false;
            hunter.moveTo(victim.pos);
        }
    }

    protected void goToReproduct(Point to, int index) {
        if (xMath.maxDist(pos, to) == 1) {
            if (sex) {
                Map.world.creatures.get(index).readyToReproduct = PERIOD_OF_PREGNANT;
                Map.world.creatures.get(index).pregnant = true;
            } else {
                readyToReproduct = PERIOD_OF_PREGNANT;
                pregnant = true;
            }
        } else {
            goTo(to);
        }
    }

    protected void goTo(Point to) {
        int x = pos.getX(), y = pos.getY();
        if (to.getX() == x) {
            if (to.getY() > y) {
                if (move(x, y + 1)) return;
                if (move(x + 1, y + 1)) return;
                if (move(x - 1, y + 1)) return;
                if (move(x - 1, y)) return;
                if (move(x + 1, y)) return;
                if (move(x + 1, y - 1)) return;
                if (move(x - 1, y - 1)) return;
                move(x, y - 1);
            } else {
                if (move(x, y - 1)) return;
                if (move(x + 1, y - 1)) return;
                if (move(x - 1, y - 1)) return;
                if (move(x + 1, y)) return;
                if (move(x - 1, y)) return;
                if (move(x - 1, y + 1)) return;
                if (move(x + 1, y + 1)) return;
                move(x, y + 1);
            }
            return;
        }
        if (to.getY() == y) {
            if (to.getX() > x) {
                if (move(x + 1, y)) return;
                if (move(x + 1, y + 1)) return;
                if (move(x + 1, y - 1)) return;
                if (move(x, y - 1)) return;
                if (move(x, y + 1)) return;
                if (move(x - 1, y + 1)) return;
                if (move(x - 1, y - 1)) return;
                if (move(x - 1, y)) return;
                sleep();
            } else {
                if (move(x - 1, y)) return;
                if (move(x - 1, y + 1)) return;
                if (move(x - 1, y - 1)) return;
                if (move(x, y - 1)) return;
                if (move(x, y + 1)) return;
                if (move(x + 1, y + 1)) return;
                if (move(x + 1, y - 1)) return;
                if (move(x + 1, y)) return;
                sleep();
            }
            return;
        }
        if (to.getX() > x && to.getY() > y) {
            if (move(x + 1, y + 1)) return;
            if (move(x, y + 1)) return;
            if (move(x + 1, y)) return;
            if (move(x - 1, y + 1)) return;
            if (move(x + 1, y - 1)) return;
            if (move(x - 1, y)) return;
            if (move(x, y - 1)) return;
            if (move(x - 1, y - 1)) return;
            sleep();
            return;
        }
        if (to.getX() < x && to.getY() > y) {
            if (move(x - 1, y + 1)) return;
            if (move(x - 1, y)) return;
            if (move(x, y + 1)) return;
            if (move(x + 1, y + 1)) return;
            if (move(x - 1, y - 1)) return;
            if (move(x, y + 1)) return;
            if (move(x - 1, y)) return;
            if (move(x + 1, y - 1)) return;
            sleep();
            return;
        }
        if (to.getX() < x && to.getY() < y) {
            if (move(x - 1, y - 1)) return;
            if (move(x, y - 1)) return;
            if (move(x - 1, y)) return;
            if (move(x - 1, y + 1)) return;
            if (move(x + 1, y - 1)) return;
            if (move(x, y + 1)) return;
            if (move(x + 1, y)) return;
            if (move(x + 1, y + 1)) return;
            sleep();
            return;
        }
        if (to.getX() > x && to.getY() < y) {
            if (move(x + 1, y - 1)) return;
            if (move(x + 1, y)) return;
            if (move(x, y - 1)) return;
            if (move(x - 1, y - 1)) return;
            if (move(x + 1, y + 1)) return;
            if (move(x, y - 1)) return;
            if (move(x + 1, y)) return;
            if (move(x - 1, y + 1)) return;
            sleep();
            return;
        }
        sleep();
    }

    abstract protected void initRation();
    abstract public void event(String msg);
}
