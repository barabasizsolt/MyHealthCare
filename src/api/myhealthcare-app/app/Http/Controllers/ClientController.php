<?php

namespace App\Http\Controllers;

use App\Models\Client;
use Illuminate\Http\Request;

class ClientController extends Controller
{

    public function currentClient($id){
        return Client::find($id);
    }
    
    public function registerClient(Request $req){
        
        $client = new Client();
        $client->name = $req->name;
        $client->address = $req->address;
        $client->email = $req->email;
        if($req->password){
            $client->password = bcrypt($req->password);
        }

        return $client->save() ? ["Result" => "Data has been saved!"] : ["Result" => "Operation failed"];
    }
}
