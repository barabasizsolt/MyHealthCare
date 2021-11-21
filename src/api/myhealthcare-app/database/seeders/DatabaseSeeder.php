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

    const DATES = ['2021/11/25', '2021/11/26', '2021/11/27', '2021/11/28', '2021/11/29', '2021/11/30', '2021/12/01', '2021/12/02'];
    const HOURS = ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'];

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
            $date = DatabaseSeeder::DATES[array_rand(DatabaseSeeder::DATES)];
            $hour = DatabaseSeeder::HOURS[array_rand(DatabaseSeeder::HOURS)];
            for($i=1; $i<=$mdNum; $i++){
                $md = $medicalDepartments[$i];
                $medic->medicalDepartments()->attach($md, ['date' => $date, 'hour' => $hour]);
            }
        });
        Appointment::factory(20)->create();
        Feedback::factory(20)->create();
    }
}
