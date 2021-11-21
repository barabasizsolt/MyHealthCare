<?php

namespace App\Http\Controllers;

use App\Models\Hospital;
use App\Models\MedicalDepartment;
use Illuminate\Http\Request;

class MedicalDepartmentController extends Controller
{
    public function uniqueName()
    {
        $names = MedicalDepartment::all('name');
        $uniqueNames = [];
        foreach($names as $name){
            if(!in_array($name, $uniqueNames))
            {
                array_push($uniqueNames, $name);
            }
        }
        return $uniqueNames;
    } 

    public function departmentsForHospitals($hospital_id = null)
    {
        return $hospital_id == null ? MedicalDepartment::all() : Hospital::find($hospital_id)->medical_departments;
    }
}
