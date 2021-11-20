<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

class ClientFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'name' => $this->faker->name(),
            'address' => $this->faker->words($nb = 4, $asText = true),
            'email' => $this->faker->email(),
            'password' => $this->faker->password()
        ];
    }
}
