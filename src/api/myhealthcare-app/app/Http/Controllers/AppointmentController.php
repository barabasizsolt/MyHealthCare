<?php

namespace App\Http\Controllers;

use App\Models\Appointment;
use App\Models\Client;
use Illuminate\Http\Request;

class AppointmentController extends Controller
{
    public function singleAppointment($id)
    {
        return Appointment::find($id);
    }

    public function clientAppointment($id)
    {
        return Client::find($id)->appointments;
    }

    public function makeAppointment(Request $req)
    {
        $appointment = new Appointment();
        $appointment->client_id = $req->client_id;
        $appointment->hospital_id = $req->hospital_id;
        $appointment->medical_department_id = $req->medical_department_id;
        $appointment->medic_id = $req->medic_id;
        $appointment->scheduled_start_date = $req->scheduled_start_date;
        $appointment->scheduled_end_date = $req->scheduled_end_date;
        $appointment->notes = $req->notes;

        return $appointment->save() ? ["Result" => "Data has been saved!"] : ["Result" => "Operation failed"];
    }
}
