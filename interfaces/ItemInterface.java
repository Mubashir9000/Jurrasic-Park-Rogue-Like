package edu.monash.fit2099.interfaces;

import game.EcoPoints;
import game.ConsumableLevel;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
public EcoPoints getCost();
public ConsumableLevel getFoodLevel();
}
