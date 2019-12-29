package com.studyboom.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.studyboom.domains.Requests;
import com.studyboom.domains.Users;
import com.studyboom.dtos.Constants;
import com.studyboom.dtos.RequestStatusDTO;
import com.studyboom.dtos.RequestsDetailsDTO;
import com.studyboom.repositories.RequestRepository;
import com.studyboom.repositories.UserRepository;
import com.studyboom.resources.RequestResources;

@Service
public class RequestsService implements RequestResources {


	private final Logger LOG = Logger.getLogger(RequestsService.class);

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<List<Requests>> getRequestsByUserId(Long userId, int pageNo, int limit) {
		try {
			Optional<Users> usersOptional = userRepository.findById(userId);
			if (!usersOptional.isPresent())
				return new ResponseEntity<List<Requests>>(HttpStatus.BAD_REQUEST);

			return new ResponseEntity<List<Requests>>(requestRepository.findByUserIdToRequests(usersOptional.get(),
					PageRequest.of(pageNo, limit, Direction.ASC, "dateCreated")), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while getting requests : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<List<Requests>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<RequestStatusDTO> addRequest(RequestsDetailsDTO requestsDetailsDTO) {
		try {
			Optional<Users> usersOptional = userRepository.findById(requestsDetailsDTO.getUserId());
			if (!usersOptional.isPresent())
				return new ResponseEntity<RequestStatusDTO>(
						new RequestStatusDTO(0, "No user found!!", requestsDetailsDTO.getUserId()),
						HttpStatus.BAD_REQUEST);

			requestRepository.save(new Requests(usersOptional.get(), requestsDetailsDTO.getRequestText(),
					Constants.REQUEST_STATUS.NOT_STARTED.name(), Constants.REQUEST_STATUS.NOT_STARTED.name(),
					LocalDateTime.now(), LocalDateTime.now()));

			return new ResponseEntity<RequestStatusDTO>(
					new RequestStatusDTO(1, "Request Added!!", requestsDetailsDTO.getUserId()), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while adding requests : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<RequestStatusDTO>(new RequestStatusDTO(1,
					"Error while adding requests : " + e.getLocalizedMessage(), requestsDetailsDTO.getUserId()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<RequestStatusDTO> modifyRequest(RequestsDetailsDTO requestsDetailsDTO) {
		try {
			Optional<Users> usersOptional = userRepository.findById(requestsDetailsDTO.getUserId());
			if (!usersOptional.isPresent())
				return new ResponseEntity<RequestStatusDTO>(
						new RequestStatusDTO(0, "No user found!!", requestsDetailsDTO.getUserId()),
						HttpStatus.BAD_REQUEST);

			Optional<Requests> requestOptional = requestRepository.findById(requestsDetailsDTO.getRequestId());
			if (!requestOptional.isPresent())
				return new ResponseEntity<RequestStatusDTO>(
						new RequestStatusDTO(0, "No request found!!", requestsDetailsDTO.getUserId()),
						HttpStatus.BAD_REQUEST);

			Requests requests = requestOptional.get();
			requests.setRequestText(requestsDetailsDTO.getRequestText());
			requests.setStatus(requestsDetailsDTO.getStatus());
			requests.setProcessed(requestsDetailsDTO.getProcessed());
			requests.setLastModified(LocalDateTime.now());

			requestRepository.save(requests);

			return new ResponseEntity<RequestStatusDTO>(
					new RequestStatusDTO(1, "Request Modified!!", requestsDetailsDTO.getUserId()), HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error while modify requests : " + e.getLocalizedMessage(), e);
			return new ResponseEntity<RequestStatusDTO>(new RequestStatusDTO(1,
					"Error while modify requests : " + e.getLocalizedMessage(), requestsDetailsDTO.getUserId()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
