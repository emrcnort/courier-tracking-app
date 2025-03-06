package com.ortakciemrecan.service;

public interface DistanceCalculatorStrategy {
    double calculate(double lat1, double lon1, double lat2, double lon2);
}
