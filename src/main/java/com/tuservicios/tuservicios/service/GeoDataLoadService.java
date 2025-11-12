package com.tuservicios.tuservicios.service;

import com.tuservicios.tuservicios.model.Ciudad;
import com.tuservicios.tuservicios.model.Departemento;
import com.tuservicios.tuservicios.repository.CiudadRepository;
import com.tuservicios.tuservicios.repository.DepartamentoRepository;
import org.apache.catalina.connector.CoyoteReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeoDataLoadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Transactional
    public int loadCiudadesFromCsv(String filePath) throws IOException{
        List<Ciudad> ciudades = new ArrayList<>();
        String line;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            br.readLine();

            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length !=2) continue;

                long departamentoId;
                try {
                    departamentoId = Long.parseLong(data[0].trim());
                }catch (NumberFormatException e){
                    continue;
                }
                String nombreCiudad = data[1].trim();

                Departemento departemento = departamentoRepository.findById(departamentoId)
                        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Departamento no encontardo con ID: " + departamentoId));

                Ciudad ciudad = new Ciudad();
                ciudad.setNombre(nombreCiudad);
                ciudad.setDepartemento(departemento);

                ciudades.add(ciudad);
                count++;

            }

        }
        ciudadRepository.saveAll(ciudades);
        return count;
    }

}
