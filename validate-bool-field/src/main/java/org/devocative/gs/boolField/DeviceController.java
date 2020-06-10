package org.devocative.gs.boolField;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

	@PutMapping("/{bool}")
	public ResponseEntity<String> updateAllActiveStatus(@PathVariable Boolean bool) {
		log.info("bool = [{}]", bool);
		return ResponseEntity.ok(String.valueOf(bool));
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody DeviceDTO dto) {
		log.info("device = [{}]", dto);
		return ResponseEntity.ok(dto.toString());
	}

	// ------------------------------

	@Getter
	@Setter
	@ToString(of = {"name", "active"})
	private static class DeviceDTO {
		private String name;
		private Boolean active;
	}
}
