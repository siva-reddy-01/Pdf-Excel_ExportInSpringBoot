package com.klef.pdf.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.pdf.Exception.InvoiceNotFoundException;
import com.klef.pdf.Repository.InvoiceRepository;
import com.klef.pdf.Service.InvoiceService;
import com.klef.pdf.model.Invoice;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository repo;

	public Invoice saveInvoice(Invoice invoice) {

		return repo.save(invoice);
	}

	public List<Invoice> getAllInvoices() {
		return repo.findAll();
	}

	public Invoice getInvoiceById(Long id) {

		Optional<Invoice> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new InvoiceNotFoundException("Invoice with id:" + id + " Not Found");
		}
	}

	public void deleteInvoiceById(Long id) {
		repo.delete(getInvoiceById(id));

	}

	public void updateInvoice(Invoice invoice) {
		repo.save(invoice);
	}

}
