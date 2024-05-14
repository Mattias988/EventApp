import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
  runApp(MaterialApp(home: EventApp()));
}

class EventApp extends StatefulWidget {
  @override
  _EventAppState createState() => _EventAppState();
}

class _EventAppState extends State<EventApp> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _dateController = TextEditingController();
  final TextEditingController _equipmentController = TextEditingController();  // Kontroler dla sprzętu

  List<dynamic> _events = [];

  Future<void> fetchEvents() async {
    var response = await http.get(Uri.parse('http://10.0.2.2:8080/events/all'));
    if (response.statusCode == 200) {
      setState(() {
        _events = json.decode(response.body);
      });
    } else {
      throw Exception('Failed to load events');
    }
  }

  Future<void> organizeEvent() async {
    var response = await http.post(
      Uri.parse('http://10.0.2.2:8080/events/organize'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'name': _nameController.text,
        'date': _dateController.text,  // Bezpośrednie przesyłanie tekstu
        'equipmentName': _equipmentController.text,  // Dodanie nazwy sprzętu do danych wysyłanych do serwera
      }),
    );

    if (response.statusCode == 200) {
      fetchEvents();
      showDialog(
        context: context,
        builder: (context) => AlertDialog(
          content: Text("Event organized successfully!"),
        ),
      );
    } else {
      showDialog(
        context: context,
        builder: (context) => AlertDialog(
          content: Text("Failed to organize event: ${response.body}"),
        ),
      );
    }
  }

  @override
  void initState() {
    super.initState();
    fetchEvents();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Event Organizer')),
      body: Column(
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              children: <Widget>[
                TextField(
                  controller: _nameController,
                  decoration: InputDecoration(labelText: 'Event Name'),
                ),
                TextField(
                  controller: _dateController,
                  decoration: InputDecoration(labelText: 'Event Date (YYYY-MM-DD)'),
                  keyboardType: TextInputType.datetime,
                ),
                TextField(
                  controller: _equipmentController,
                  decoration: InputDecoration(labelText: 'Equipment Name'),
                ),
                ElevatedButton(
                  onPressed: organizeEvent,
                  child: Text('Organize Event'),
                ),
              ],
            ),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: _events.length,
              itemBuilder: (context, index) {
                return ListTile(
                  title: Text(_events[index]['name']),
                  subtitle: Text('Data: ${_events[index]['date']}'),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
