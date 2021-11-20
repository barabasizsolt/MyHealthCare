<?php

namespace Database\Factories;

use App\Models\Appointment;
use Illuminate\Database\Eloquent\Factories\Factory;

class FeedbackFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        $appointments = Appointment::pluck('id')->toArray();
        return [
            'appointment_id' => $this->faker->randomElement($appointments),
            'message' => $this->faker->text($maxNbChars = 50),
            'billing' => $this->faker->randomDigit($min = 100, $max = 1000),
        ];
    }
}
