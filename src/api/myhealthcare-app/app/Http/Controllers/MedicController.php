<?php

namespace App\Http\Controllers;

use App\Models\Medic;
use App\Models\MedicalDepartment;
use Illuminate\Http\Request;

class MedicController extends Controller
{
    public function all($medical_dep_id = null)
    {
        return $medical_dep_id == null ? Medic::all() : MedicalDepartment::find($medical_dep_id)->medics;
    }
}
