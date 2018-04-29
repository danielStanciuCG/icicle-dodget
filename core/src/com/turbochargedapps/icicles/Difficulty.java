package com.turbochargedapps.icicles;

import static com.turbochargedapps.icicles.Constants.*;

public enum Difficulty {
    COLD(COLD_DIFFICULTY_LABEL, COLD_ICICLE_SPAWNS_PER_SECOND),
    COLDER(COLDER_DIFFICULTY_LABEL, COLDER_ICICLE_SPAWNS_PER_SECOND),
    COLDEST(COLDEST_DIFFICULTY_LABEL, COLDEST_ICICLE_SPAWNS_PER_SECOND);

    private String difficultyLabel;
    private int icicleSpawnRate;

    Difficulty(String difficultyLabel, int icicleSpawnRate) {
        this.difficultyLabel = difficultyLabel;
        this.icicleSpawnRate = icicleSpawnRate;
    }

    /**
     * Returns the difficulty label
     * @return String
     */
    public String getDifficultyLabel() {
        return difficultyLabel;
    }

    /**
     * Returns the icicle spawn rate
     * @return int
     */
    public int getIcicleSpawnRate() {
        return icicleSpawnRate;
    }
}
