package com.optilife.model.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"usuario", "suscripcion"})
@ToString(exclude = {"usuario", "suscripcion"})
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "monto", nullable = false)
    private Integer monto;

    // Relaciones

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne(mappedBy = "pago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Suscripcion suscripcion;
}