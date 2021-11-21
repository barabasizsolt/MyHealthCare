<?php

namespace App\Http\Controllers;

use App\Models\Appointment;
use App\Models\Hospital;
use App\Models\MedicalDepartment;
use Illuminate\Http\Request;

class HospitalController extends Controller
{
    public function all($medical_dep_name = null)
    {
        $medical_dep_id = MedicalDepartment::get()->where('name', '=', $medical_dep_name)->pluck('id')->get(0);
        $appointments = Appointment::all()->where('medical_department_id', '=', $medical_dep_id);
        $hospitals = collect();
        foreach($appointments as $appointment){
            $hospitals->push(Hospital::find($appointment->hospital_id));
        }
        return $medical_dep_name == null ? Hospital::all() : $hospitals;
    }

    
}
