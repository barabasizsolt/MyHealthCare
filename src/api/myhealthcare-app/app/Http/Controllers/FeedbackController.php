<?php

namespace App\Http\Controllers;

use App\Models\Client;
use App\Models\Feedback;
use Illuminate\Http\Request;

class FeedbackController extends Controller
{
    public function singleFeedback($id)
    {
        return Feedback::find($id);
    }

    public function clientFeedbacks($client_id)
    {
        $appointments = Client::find($client_id)->appointments;
        $feedbacks = collect();
        foreach($appointments as $appointment){
            $feedbacks->push($appointment->feedbacks);
        }
        return $feedbacks;
    }
}
