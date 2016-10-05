package com.example.cawate14.cs4001project3;

// Callback interface for GameModel
public interface GameViewControl {

    // Tile i should be flipped to back
    void flipTile(int i);
    // Tile i should be flipped to front
    void unFlipTile(int i);

    // Tiles i and j have been matched
    void tilesMatched(int i, int j);
    // Tiles i and j are not a match
    void tilesNotMatched(int i, int j);

    // All pictures matched, game finished
    void gameFinished();

}
