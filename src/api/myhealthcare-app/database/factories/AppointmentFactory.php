<?php

namespace Database\Factories;

use App\Models\Client;
use App\Models\Hospital;
use App\Models\MedicalDepartment;
use App\Models\Medic;
use Illuminate\Database\Eloquent\Factories\Factory;

class AppointmentFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        $clients = Client::pluck('id')->toArray();
        $hospitals = Hospital::pluck('id')->toArray();
        $mDepartments = MedicalDepartment::pluck('id')->toArray();
        $medics = Medic::pluck('id')->toArray();
        return [
            'client_id' => $this->faker->randomElement($clients),
            'hospital_id' => $this->faker->randomElement($hospitals),
            'medical_department_id' => $this->faker->randomElement($mDepartments),
            'medic_id' => $this->faker->randomElement($medics),
            'scheduled_start_date' => $this->faker->dateTimeBetween($startDate = '-5 years', $endDate = '-1 years', $timezone = null)->format('Y-m-d'),
            'scheduled_end_date' => $this->faker->dateTimeBetween($startDate = '-1 years', $endDate = 'now', $timezone = null)->format('Y-m-d'),
            'notes' => $this->faker->text($maxNbChars = 150)
        ];
    }
}
