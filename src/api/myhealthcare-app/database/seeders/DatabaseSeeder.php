<?php

namespace Database\Seeders;

use App\Models\Appointment;
use App\Models\Client;
use App\Models\Feedback;
use App\Models\Hospital;
use App\Models\MedicalDepartment;
use App\Models\Medic;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        Client::factory(20)->create();
        Hospital::factory(20)->create();
        MedicalDepartment::factory(20)->create();
        Medic::factory(20)->create()->each(function($medic){
            $medicalDepartments = MedicalDepartment::get()->all();
            shuffle($medicalDepartments);
            $mdNum = rand(1,3);
            for($i=1; $i<=$mdNum; $i++){
                $md = $medicalDepartments[$i];
                $medic->medicalDepartments()->attach($md);
            }
        });
        Appointment::factory(20)->create();
        Feedback::factory(20)->create();
    }
}
