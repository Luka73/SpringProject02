package br.com.springproject02.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import br.com.springproject02.dto.RelatorioCompromissoDto;
import br.com.springproject02.entity.Compromisso;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class CompromissoReport {
    //método para retornar um relatório PDF em formato de byte (formato de arquivo em memória)
    //O controller q vai pegar esse arquivo e decidir o que faz com ele
    public ByteArrayInputStream getPdf(RelatorioCompromissoDto dto) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, out);

        document.open(); //documento aberto!

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        document.add(new Paragraph("Relatorio de Compromissos"));
        document.add(new Paragraph("Período de: " + sdf.format(dto.getDataInicio())));
        document.add(new Paragraph("Até: " + sdf.format(dto.getDataFim())));

        document.add(new Paragraph("Nome do Usuário: " + dto.getUsuario().getNome()));
        document.add(new Paragraph("Email: " + dto.getUsuario().getEmail()));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        table.addCell("Nome do Compromisso");
        table.addCell("Data");
        table.addCell("Hora");
        table.addCell("Tipo");
        table.addCell("Prioridade");

        for (Compromisso item : dto.getCompromissos()) {
            table.addCell(item.getNome());
            table.addCell(sdf.format(item.getData()));
            table.addCell(item.getHora());
            table.addCell(item.getTipo().toString());
            table.addCell(item.getPrioridade().toString());
        }

        document.add(table); //incluindo a tabela no documento PDF

        document.add(new Paragraph("Registros obtidos: " + dto.getCompromissos().size()));

        document.close();
        writer.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
