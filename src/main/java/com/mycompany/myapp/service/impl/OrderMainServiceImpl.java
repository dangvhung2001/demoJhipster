package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.OrderMain;
import com.mycompany.myapp.repository.OrderMainRepository;
import com.mycompany.myapp.service.OrderMainService;
import com.mycompany.myapp.service.dto.OrderMainDTO;
import com.mycompany.myapp.service.mapper.OrderMainMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.OrderMain}.
 */
@Service
@Transactional
public class OrderMainServiceImpl implements OrderMainService {

    private final Logger log = LoggerFactory.getLogger(OrderMainServiceImpl.class);

    private final OrderMainRepository orderMainRepository;

    private final OrderMainMapper orderMainMapper;

    public OrderMainServiceImpl(OrderMainRepository orderMainRepository, OrderMainMapper orderMainMapper) {
        this.orderMainRepository = orderMainRepository;
        this.orderMainMapper = orderMainMapper;
    }

    @Override
    public OrderMainDTO save(OrderMainDTO orderMainDTO) {
        log.debug("Request to save OrderMain : {}", orderMainDTO);
        OrderMain orderMain = orderMainMapper.toEntity(orderMainDTO);
        orderMain = orderMainRepository.save(orderMain);
        return orderMainMapper.toDto(orderMain);
    }

    @Override
    public OrderMainDTO update(OrderMainDTO orderMainDTO) {
        log.debug("Request to update OrderMain : {}", orderMainDTO);
        OrderMain orderMain = orderMainMapper.toEntity(orderMainDTO);
        orderMain = orderMainRepository.save(orderMain);
        return orderMainMapper.toDto(orderMain);
    }

    @Override
    public Optional<OrderMainDTO> partialUpdate(OrderMainDTO orderMainDTO) {
        log.debug("Request to partially update OrderMain : {}", orderMainDTO);

        return orderMainRepository
            .findById(orderMainDTO.getId())
            .map(existingOrderMain -> {
                orderMainMapper.partialUpdate(existingOrderMain, orderMainDTO);

                return existingOrderMain;
            })
            .map(orderMainRepository::save)
            .map(orderMainMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderMainDTO> findAll() {
        log.debug("Request to get all OrderMains");
        return orderMainRepository.findAll().stream().map(orderMainMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<OrderMainDTO> findAllWithEagerRelationships(Pageable pageable) {
        return orderMainRepository.findAllWithEagerRelationships(pageable).map(orderMainMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderMainDTO> findOne(Long id) {
        log.debug("Request to get OrderMain : {}", id);
        return orderMainRepository.findOneWithEagerRelationships(id).map(orderMainMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderMain : {}", id);
        orderMainRepository.deleteById(id);
    }
}
