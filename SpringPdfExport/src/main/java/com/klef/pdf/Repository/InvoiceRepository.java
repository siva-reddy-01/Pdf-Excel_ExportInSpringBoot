package com.klef.pdf.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.pdf.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	

}
