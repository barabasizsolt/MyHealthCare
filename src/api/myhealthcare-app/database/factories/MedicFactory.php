<?php

namespace Database\Factories;

use App\Models\Medic;
use Illuminate\Database\Eloquent\Factories\Factory;

class MedicFactory extends Factory
{
    /**
     * The name of the factory's corresponding model.
     *
     * @var string
     */
    protected $model = Medic::class;

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'name' => $this->faker->name(),
            'contact' => $this->faker->numerify('###-###-###'),
            'hired_date' => $this->faker->dateTimeBetween($startDate = '-10 years', $endDate = 'now', $timezone = null)->format('Y-m-d')
        ];
    }
}
