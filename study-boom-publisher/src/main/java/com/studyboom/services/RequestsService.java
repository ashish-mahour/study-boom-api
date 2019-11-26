package com.studyboom.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<Set<Requests>> getRequestsByUserId(Long userId, int pageNo, int limit) {
		Optional<Users> usersOptional = userRepository.findById(userId);
		if (!usersOptional.isPresent())
			return new ResponseEntity<Set<Requests>>(HttpStatus.BAD_REQUEST);

		Set<Requests> requests = new TreeSet<>();
		requests.addAll(requestRepository.findByUserIdToRequests(usersOptional.get(),
				PageRequest.of(pageNo, limit, Direction.ASC, "dateCreated")));
		return new ResponseEntity<Set<Requests>>(requests, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestStatusDTO> addRequest(RequestsDetailsDTO requestsDetailsDTO) {
		Optional<Users> usersOptional = userRepository.findById(requestsDetailsDTO.getUserId());
		if (!usersOptional.isPresent())
			return new ResponseEntity<RequestStatusDTO>(
					new RequestStatusDTO(0, "No user found!!", requestsDetailsDTO.getUserId()), HttpStatus.BAD_REQUEST);

		requestRepository.save(new Requests(usersOptional.get(), requestsDetailsDTO.getRequestText(),
				Constants.REQUEST_STATUS.NOT_STARTED.name(), Constants.REQUEST_STATUS.NOT_STARTED.name(),
				LocalDateTime.now(), LocalDateTime.now()));

		return new ResponseEntity<RequestStatusDTO>(
				new RequestStatusDTO(1, "Request Added!!", requestsDetailsDTO.getUserId()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestStatusDTO> modifyRequest(RequestsDetailsDTO requestsDetailsDTO) {
		Optional<Users> usersOptional = userRepository.findById(requestsDetailsDTO.getUserId());
		if (!usersOptional.isPresent())
			return new ResponseEntity<RequestStatusDTO>(
					new RequestStatusDTO(0, "No user found!!", requestsDetailsDTO.getUserId()), HttpStatus.BAD_REQUEST);

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
	}

}
