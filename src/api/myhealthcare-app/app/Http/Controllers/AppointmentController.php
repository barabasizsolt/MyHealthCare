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
}
