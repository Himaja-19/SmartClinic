-- ================================
-- SmartClinic Database Schema
-- Run this FULLY before starting app
-- ================================

DROP DATABASE IF EXISTS smartclinic;
CREATE DATABASE smartclinic;
USE smartclinic;

-- Doctors table
CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    available_days VARCHAR(100),
    email VARCHAR(100)
);

-- Patients table
CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL
);

-- Appointments table
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'Scheduled',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

-- Prescriptions table
CREATE TABLE prescriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    medicine VARCHAR(200),
    dosage VARCHAR(100),
    notes TEXT,
    FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE CASCADE
);

-- ========================
-- 6 UNIQUE Doctors
-- ========================
INSERT INTO doctors (name, specialization, available_days, email) VALUES
('Dr. Priya Sharma',   'Cardiology',        'Mon, Wed, Fri',  'priya@smartclinic.com'),
('Dr. Rahul Nair',     'General Medicine',  'Tue, Thu, Sat',  'rahul@smartclinic.com'),
('Dr. Sneha Reddy',    'Dermatology',       'Mon, Thu, Fri',  'sneha@smartclinic.com'),
('Dr. Arjun Mehta',    'Orthopedics',       'Mon, Tue, Wed',  'arjun@smartclinic.com'),
('Dr. Kavitha Rao',    'Neurology',         'Wed, Thu, Sat',  'kavitha@smartclinic.com'),
('Dr. Suresh Iyer',    'Pediatrics',        'Mon, Tue, Fri',  'suresh@smartclinic.com');

-- ========================
-- 5 UNIQUE Patients
-- ========================
INSERT INTO patients (name, age, email, phone) VALUES
('Himaja R',    22, 'himaja@email.com',   '9876543210'),
('Arjun Kumar', 30, 'arjun@email.com',    '9123456789'),
('Sita Devi',   45, 'sita@email.com',     '9345678901'),
('Ravi Teja',   28, 'ravi@email.com',     '9567890123'),
('Meena Joshi', 35, 'meena@email.com',    '9789012345');

-- ========================
-- Sample Appointments (all unique combos)
-- ========================
INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES
(1, 1, '2026-05-20', 'Scheduled'),
(2, 2, '2026-05-21', 'Scheduled'),
(3, 3, '2026-05-22', 'Completed'),
(4, 4, '2026-05-23', 'Scheduled'),
(5, 5, '2026-05-24', 'Cancelled');
