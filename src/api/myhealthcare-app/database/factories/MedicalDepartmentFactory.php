<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use App\Models\Hospital;

class MedicalDepartmentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        $hospitals = Hospital::pluck('id')->toArray();
        return [
            'name' => $this->faker->words($nb = 2, $asText = true),
            'description' => $this->faker->text($maxNbChars = 100),
            'contact' => $this->faker->numerify('###-###-###'),
            'hospital_id' => $this->faker->randomElement($hospitals)
        ];
    }
}

