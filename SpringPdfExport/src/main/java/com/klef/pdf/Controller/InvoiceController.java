package com.klef.pdf.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.klef.pdf.Exception.InvoiceNotFoundException;
import com.klef.pdf.ServiceImpl.InvoiceServiceImpl;
import com.klef.pdf.model.Invoice;
import com.klef.pdf.view.InvoiceDataExcelExport;
import com.klef.pdf.view.InvoiceDataPdfExport;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceServiceImpl service;

	@GetMapping("/")
	public String showHomePage() {
		return "homepage";
	}

	@GetMapping("/register")
	public String showRegistration() {
		return "registerInvoicePage";
	}

	@PostMapping("/save")
	public String saveInvoice(@ModelAttribute Invoice invoice, Model model) {

		service.saveInvoice(invoice);
		Long id = service.saveInvoice(invoice).getId();
		String message = "Records with id:'" + id + "' is saved Sucuessfully";
		model.addAttribute("message",message);
		return "registerInvoicePage";

	}

	@GetMapping("/getAllInvoices")
	public String getAllInvoices(@RequestParam(value = "message", required = false) String message, Model model) {

		List<Invoice> invoices = service.getAllInvoices();
		model.addAttribute("list", invoices);
		model.addAttribute("message", message);
		return "allInvoicesPage";
	}

	@PostMapping("/update")
	public String updateInvoice(@ModelAttribute Invoice invoice, RedirectAttributes attributes) {
		service.updateInvoice(invoice);
		Long id = invoice.getId();
		attributes.addAttribute("message", "Ivoice with id: '" + id + "' is updated sucuessfully!");
		return "redirect:getAllInvoices";
	}

	@GetMapping("/delete")
	public String deleteInvoice(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteInvoiceById(id);
			attributes.addAttribute("message", "Invoice with id: '" + id + "' removed sucuessfully!");

		} catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:getAllInvoices";
	}

	@GetMapping("/edit")
	public String editpage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {
			Invoice invoice = service.getInvoiceById(id);
			model.addAttribute("invoice", invoice);
			page = "editInvoicePage";
		} catch (InvoiceNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:getAllInvoices";
		}
		return page;
	}

	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new InvoiceDataPdfExport());
		// read data from DB
		List<Invoice> list = service.getAllInvoices();
		// send to pdfImpl class
		mav.addObject("list", list);
		return mav;
	}
	
	@GetMapping("/excelExport")
	public ModelAndView exportToExcel() {
	    ModelAndView mav = new ModelAndView();
	    mav.setView(new InvoiceDataExcelExport());
	  //read data from DB
	    List<Invoice> list= service.getAllInvoices();
	  //send to excelImpl class
	    mav.addObject("list", list);
	    return mav; 
	}

}
