package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import com.betrybe.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/** Controller for the museum resource. */

@RestController
@RequestMapping("/museums")
public class MuseumController {
  private MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MuseumDto create(@RequestBody MuseumCreationDto museumCreationDto) {
    Museum museum = ModelDtoConverter.dtoToModel(museumCreationDto);
    return ModelDtoConverter.modelToDto(museumService.createMuseum(museum));
  }

  /** Returns the closest museum to the given coordinate. */

  @GetMapping("/closest")
  @ResponseStatus(HttpStatus.OK)
  public MuseumDto getClosestMuseum(
      @RequestParam(name = "lng") double lng,
      @RequestParam(name = "lat") double lat,
      @RequestParam(name = "max_dist_km") double maxDistKm
  ) {
    Coordinate coordinate = new Coordinate(lng, lat);
    return ModelDtoConverter.modelToDto(
      museumService.getClosestMuseum(coordinate, maxDistKm)
    );
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public MuseumDto getMuseum(
      @PathVariable(name = "id") long id
  ) {
    return ModelDtoConverter.modelToDto(museumService.getMuseum(id));
  }
}
