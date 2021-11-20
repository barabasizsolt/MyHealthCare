<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

class HospitalFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'name' => $this->faker->word(),
            'contact' => $this->faker->numerify('###-###-###'),
            'description' => $this->faker->text($maxNbChars = 200),
            'address' => $this->faker->words($nb = 4, $asText = true),
            'longitude_coordinate' => $this->faker->randomFloat($nbMaxDecimals = 5, $min = 0, $max = 90),
            'latitude_coordinate' => $this->faker->randomFloat($nbMaxDecimals = 5, $min = 0, $max = 90)
        ];
    }
}
