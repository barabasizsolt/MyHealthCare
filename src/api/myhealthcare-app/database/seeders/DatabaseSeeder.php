<?php

namespace Database\Seeders;

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
        // \App\Models\User::factory(10)->create();
        $h = new Hospital();
        $h->name = 'Hospital';
        $h->contact = '08748';
        $h->description = 'auybeniofpfwf';
        $h->address = 'bcuec';
        $h->longitude_coordinate = 'cervre';
        $h->latitude_coordinate = 'efweewf';
        $h->save();

        $md = new MedicalDepartment();
        $md->name = 'md1';
        $md->description = 'bvreuoivpekwrivub';
        $md->contact='054554';
        $md->hospital_id = 1;
        $md->save();
        
        $md2 = new MedicalDepartment();
        $md2->name = 'md2';
        $md2->description = 'bvdfewfreuoivpekwrivub';
        $md2->contact='054552';
        $md2->hospital_id = 2;
        $md2->save();

        $m = new Medic();
        $m->name = 'Janos';
        $m->contact = '48414949';
        $m->hired_date = '2014/05/06';
        $m->save();

        $m2 = new Medic();
        $m2->name = 'Elek';
        $m2->contact = '48114949';
        $m2->hired_date = '2015/05/06';
        $m2->save();

        $md->medics()->attach($m);
        $md->medics()->attach($m2);
        $md2->medics()->attach($m);
    }
}
